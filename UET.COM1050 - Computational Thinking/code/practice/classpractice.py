import math

class Hero:
    def __init__(self, name: str, hp: int, atk: int) -> None:
        self.name = name
        self.hp = hp
        self.atk = atk

    def take_damage(self, damage : int):
        self.hp -= damage
        self.hp = max(0, self.hp)

    def attack(self, other):
        other.take_damage(self.atk)

    def __str__(self):
        return f"Tên: {self.name} - HP: {self.hp} - ATK: {self.atk}"
    
    def __gt__(self, other):
        return self.hp > other.hp

class Warrior(Hero):
    def __init__(self, name: str, hp: int, atk: int, armor: int) -> None:
        super().__init__(name, hp, atk)
        self.armor = armor

    def take_damage(self, damage):
        actual_damage = max(0, damage - self.armor)
        super().take_damage(actual_damage)

class Mage(Hero):
    def __init__(self, name: str, hp: int, atk: int, mana: int) -> None:
        super().__init__(name, hp, atk)
        self.mana = mana

    def attack(self, other):
        if self.mana > 0:
            other.take_damage(self.atk * 2)
            self.mana -= 10

        else:
            super().attack(other)

class Shape:
    def __init__(self, name: str) -> None:
        self.name = name

    def area(self) -> float:
        return 0.0
    
    def perimeter(self) -> float:
        return 0.0
    
    def __str__(self) -> str: 
        return f"[{self.name}] Diện tích: {self.area():.2f} - Chu vi: {self.perimeter():.2f}"

    def __gt__(self, other) -> bool:
        return self.area() > other.area()

class Rectangle(Shape):
    def __init__(self, name: str, width: float, height: float) -> None:
        super().__init__(name)
        self.width = width
        self.height = height

    def area(self) -> float:
        return self.width * self.height
    
    def perimeter(self) -> float:
        return (self.width + self.height) * 2
    
class Circle(Shape):
    def __init__(self, name: str,  radius: float) -> None:
        super().__init__(name)
        self.radius = radius

    def area(self) -> float:
        return math.pi * pow(self.radius, 2)
    
    def perimeter(self) -> float:
        return 2 * math.pi * self.radius
    
class Square(Rectangle):
    def __init__(self, name: str, side: float) -> None:
        super().__init__(name, side, side)


if __name__ == "__main__":
    r1 = Rectangle("HCN 1", width=5, height=10)
    c1 = Circle("Tron 1", radius=5)
    s1 = Square("Vuong 1", side=7)

    print(r1)
    print(c1)
    print(s1)

    print("\n--- So sánh Diện tích ---")
    if c1 > r1:
        print(f"{c1.name} to hơn {r1.name}")
    else:
        print(f"{r1.name} to hơn {c1.name}")
    
    print(Square.mro())