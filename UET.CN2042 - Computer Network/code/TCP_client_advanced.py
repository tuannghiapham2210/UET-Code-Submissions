import socket
import threading

def receive_messages(sock, stop_event):
    """
    Background thread dedicated to continuously receiving messages from the server.
    Thanks to this thread, incoming messages are displayed immediately even if the client is not typing.
    """
    while not stop_event.is_set():
        try:
            data = sock.recv(1024)
            if not data:
                print("\n[-] Server closed the connection. Press Enter to exit.")
                stop_event.set()
                break
            
            message = data.decode("utf-8")
            # Print the received message and reprint the 'Client > ' prompt for convenient typing
            print(f"\nServer > {message}\nClient > ", end="", flush=True)
        except OSError:
            # Occurs when the socket is closed from the main thread
            break
        except Exception as e:
            if not stop_event.is_set():
                print(f"\n[!] Error receiving data from server: {e}")
            break

def run_client():
    # 1. Configure Server IP address and Port
    default_ip = "127.0.0.1"
    server_ip = input(f"Enter Server IP address (press Enter for '{default_ip}'): ").strip()
    if not server_ip:
        server_ip = default_ip

    port = 8080

    # 2. Create TCP Socket (IPv4)
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    try:
        # 3. Connect to the server
        print(f"[*] Connecting to server at {server_ip}:{port}...")
        client_socket.connect((server_ip, port))
        print("[+] Successfully connected to the server!")
        print("[i] Type your message and press Enter to send. Type 'exit' or 'quit' to disconnect.\n" + "-" * 50)

        # Synchronization event flag between the two threads
        stop_event = threading.Event()

        # 4. Start an independent thread to receive messages (runs concurrently)
        recv_thread = threading.Thread(
            target=receive_messages, 
            args=(client_socket, stop_event), 
            daemon=True
        )
        recv_thread.start()

        # 5. Main thread reads input from the keyboard and sends it
        while not stop_event.is_set():
            try:
                message = input("Client > ").strip()
            except EOFError:
                break

            # If the server disconnected while the client was waiting for user input
            if stop_event.is_set():
                break

            # Skip empty messages
            if not message:
                continue

            # Send message to the server
            client_socket.sendall(message.encode("utf-8"))

            # If the user wants to exit
            if message.lower() in ("exit", "quit"):
                print("[*] Closing connection...")
                stop_event.set()
                break

    except ConnectionRefusedError:
        print(f"\n[!] Could not connect to {server_ip}:{port}!")
        print(f"    -> Tip: Check if the server is running, the IP is correct, and Windows Firewall is not blocking port {port}.")
    except socket.gaierror:
        print(f"\n[!] Invalid IP address: '{server_ip}'")
    except KeyboardInterrupt:
        print("\n[*] Program cancelled by user (Ctrl + C).")
    except Exception as e:
        print(f"\n[!] An error occurred: {e}")
    finally:
        # 6. Cleanly close the client socket
        client_socket.close()
        print("[*] Client connection closed. Goodbye!")

if __name__ == "__main__":
    run_client()