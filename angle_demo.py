from numpy import arctan

Ax = 1
Ay = 2
Avx = 1.5
Avy = 3 # second point for vector
ASlope = (Avy-Ay) / (Avx-Ax)
Aangle = arctan(ASlope)

Bx = 5
By = 4
Bvx = 6
Bvy = 3.5
BSlope = (Bvy-By) / (Bvx-Bx)
Bangle = arctan(BSlope)

print("A : ", Aangle)
print("B : ", Bangle)