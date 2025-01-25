#!/usr/bin/env python3

from math import sqrt

class Dot3D:

    def __init__(self, x, y, z, label="x"):
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
