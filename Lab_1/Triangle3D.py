#!/usr/bin/env python3

from math import sqrt

class Dot3D:

    def __init__(self, x, y, z, label=None):
        self.x = x
        self.y = y
        self.z = z
        self.label = label

    def distance_to(self, other):
        result = sqrt((other.x - self.x)**2 + (other.y - self.y)**2 + (other.z - self.z)**2)
        return result

    def add_vector(self, other):
        new_x = self.x + other.x
        new_y = self.y + other.y
        new_z = self.z + other.z
        new_label = f"{self.label}+{other.label}"
        return Dot3D(new_x, new_y, new_z, new_label)

class Triangle3D:

    def __init__(self, dot1, dot2, dot3):
        self.dot1 = dot1
        self.dot2 = dot2
        self.dot3 = dot3
        self.edge1 = dot1.distance_to(dot2)
        self.edge2 = dot1.distance_to(dot3)
        self.edge3 = dot2.distance_to(dot3)
        self.s = (self.edge1 + self.edge2 + self.edge3) / 2

    def calculate_perimeter(self):
        perimeter = self.edge1 + self.edge2 + self.edge3
        return perimeter

    def calculate_area(self):
        area = sqrt(self.s * (self.s - self.edge1) * (self.s - self.edge2) * (self.s - self.edge3))
        return area
