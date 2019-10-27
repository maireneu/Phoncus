class Pokemon:
    def __init__(self, name):
        self.name = name
    def attack(self):
        print("Pokemon attack!")

class ElectricPokemon(Pokemon):
    pass
class WaterPokemon(Pokemon):
    def attack(self):
        print("WaterPokemon attack!")
class FlyingPokemon(Pokemon):
    pass

eevie = Pokemon("Eevie")
misty = WaterPokemon("Misty")
pikachu = ElectricPokemon("Pikachu")
charizard = FlyingPokemon("Charizard")

pokemons = [eevie, pikachu, misty, charizard]
for pokemon in pokemons:
    pokemon.attack()
