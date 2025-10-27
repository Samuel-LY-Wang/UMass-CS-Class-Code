from my_sprite import my_sprite

class sprite_collection():
    def __init__(self):
        self.sprites: list[my_sprite]=[]
    def add(self, to_add: my_sprite):
        if not isinstance(to_add, my_sprite):
            raise TypeError("can only add my_sprite objects to collection")
        self.sprites.append(to_add)
    def get_collection(self):
        return self.sprites
    def __getitem__(self, id: int):
        return self.sprites[id]
    def __setitem__(self, id: int, to_set: my_sprite):
        if not isinstance(id, int):
            raise TypeError("index must be an integer")
        if not isinstance(to_set, my_sprite):
            raise TypeError("search target must be a my_sprite object")
        self.sprites[id] = to_set
    def __len__(self):
        return len(self.sprites)
    def __str__(self):
        return str(self.sprites)
    def search(self, target: my_sprite):
        if not isinstance(target, my_sprite):
            raise TypeError("search target must be a my_sprite object")
        valid_sprites=[]
        for sprite in self.sprites:
            if sprite==target:
                is_present = False
                for s in valid_sprites:
                    if s is sprite:
                        is_present = True
                        break
                if not is_present:
                    valid_sprites.append(sprite)
        return valid_sprites