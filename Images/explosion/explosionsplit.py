from pygame import *
thing = image.load("explosion.png")
newSurface = Surface((480, 288), SRCALPHA)
newSurface.blit(thing, (0, 0))
c = 0

for i in range(0, 288, 96):
    name = "explosion_" + str(c) + ".png"
    image.save(newSurface.subsurface(0, i, 96, 96), name)
    c += 5

x = 1
for i in range(0,288,96):
    name = "explosion_" + str(x) + ".png"
    image.save(newSurface.subsurface(96, i, 96, 96), name)
    x += 5

y = 2  
for i in range(0,288,96):
    name = "explosion_" + str(y) + ".png"
    image.save(newSurface.subsurface(192, i, 96, 96), name)
    y += 5

a = 3
for i in range(0,288,96):
    name = "explosion_" + str(a) + ".png"
    image.save(newSurface.subsurface(288, i, 96, 96), name)
    a+= 5

b = 4
for i in range(0,192,96):
    name = "explosion_" + str(b) + ".png"
    image.save(newSurface.subsurface(384, i, 96, 96), name)
    b += 5

