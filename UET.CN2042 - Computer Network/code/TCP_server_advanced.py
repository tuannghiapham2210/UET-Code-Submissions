import socket
import threading

def receive_messages(client_sock, stop_event):
    """
    Background thread dedicated to continuously receiving messages from the client.
    Allows the server to display incoming client messages immediately without waiting for console input.
    """
    while not stop_event.is_set():
        try:
            data = client_sock.recv(1024)
            if not data:
                print("\n[-] Client disconnected. Press Enter to exit.")
                stop_event.set()
                break

            message = data.decode("utf-8")
            # Print the received message and reprint the 'Server > ' prompt for convenient typing
            print(f"\nClient > {message}\nServer > ", end="", flush=True)
        except OSError:
            # Occurs when the socket is closed from another thread
            break
        except Exception as e:
            if not stop_event.is_set():
                print(f"\n[!] Error receiving data from client: {e}")
            break

def run_server():
    # 1. Configure Host IP and Port
    host = "0.0.0.0"
    port = 8080

    # 2. Create TCP Socket (IPv4)
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # Allow immediate reuse of the port after restart
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

    try:
        # 3. Bind socket to address and listen for incoming connections
        server_socket.bind((host, port))
        server_socket.listen(1)
        print(f"[*] Server listening on {host}:{port}...")
        print("[*] Waiting for an incoming client connection...")

        # Accept incoming client connection
        client_socket, client_address = server_socket.accept()
        print(f"[+] Client connected from {client_address[0]}:{client_address[1]}!")
        print("[i] Type your message and press Enter to send. Type 'exit' or 'quit' to disconnect.\n" + "-" * 50)

        # Synchronization event flag between threads
        stop_event = threading.Event()

        # 4. Start an independent thread to receive messages from the client (runs concurrently)
        recv_thread = threading.Thread(
            target=receive_messages,
            args=(client_socket, stop_event),
            daemon=True
        )
        recv_thread.start()

        # 5. Main thread dedicated to reading console input and sending responses to client
        while not stop_event.is_set():
            try:
                message = input("Server > ").strip()
            except EOFError:
                break

            # If client disconnected while server was waiting for user input
            if stop_event.is_set():
                break

            # Skip empty messages
            if not message:
                continue

            # Send message to the client
            client_socket.sendall(message.encode("utf-8"))

            # If the user wants to exit
            if message.lower() in ("exit", "quit"):
                print("[*] Closing connection...")
                stop_event.set()
                break

    except KeyboardInterrupt:
        print("\n[*] Server stopped by user (Ctrl + C).")
    except Exception as e:
        print(f"\n[!] Server error: {e}")
    finally:
        # 6. Cleanly close sockets
        if 'client_socket' in locals():
            client_socket.close()
        server_socket.close()
        print("[*] Server shutdown cleanly. Goodbye!")

if __name__ == "__main__":
    run_server()

