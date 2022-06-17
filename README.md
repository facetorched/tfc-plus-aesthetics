# TFC+ Aesthetics
World generated decoration blocks for TFC+ to make the world prettier

# Instructions for configuration

### General Configuration
Found at the top of `config/TFCPlusAesthetics.cfg` and contain a description and a default value. These control overall settings for the mod such as optional recipes, rarity multipliers and a setting that can remove TFC+ shrubs.

### Plants
The generation of plants in this mod is highly configurable. Each plant has many parameters that define where and how it should generate. At the top of the list are custom generators with names as such `_z0`, `_z1`, `_z2` and so on. These are in place to allow the player or modpack creators to cause plants or any other decoration to generate with high customization on the surface of a TFC+ world. The blocks that are placed by these custom generators need not be from this mod or even TFC+.

The following list describes the parameters that control generation for each plant:

- **biomes**
  - The biomes that the plant can spawn in separated by new lines. valid biomes are `Ocean, Plains, Lake, High Hills, Swamp, River, Hell, Beach, Gravel Beach, High Hills Edge, Rolling Hills, Mountains, Mountains Edge, High Plains, Deep Ocean, Mountain Range, Mountain Range Edge, Foothills, Shore, Salt Swamp, Lakeshore, Riverbank, River Source, Estuary`. To add all valid biomes use the `All` keyword and to exclude a specific biome, prefix it with `!`, for example: `!Hell`
- **blockName**
  - The name of the block to generate. For example `terrafirmacraftplus:undergrowth`
- **dispersion**
  - The rarity of plants within a given generated cluster. If `dispersion=n` every `n` blocks will have a plant. If `dispersion=1`, every block within a cluster will contain a plant. This parameter is ignored for epiphytic plants.
- **forestGen**
  - The weight associated with forest generation. This parameter directly modifies `rarity` in a way that mimics how TFC+ tree generation occurs. The higher this number the more likely this plant will generate alongside trees. It is recommended to use floating point values between -1.0 and 1.0 since the TFC+ modifier is raised to this power. For example, if `forestGen=0.0` the plant's rarity will not be affected since any number raised to the zero power is 1. If `forestGen=1.0` the plant will generate in similar locations to trees. Negative values will make plants generate away from trees. 
- **growOnBlocks**
  - The blocks or OreDictionaries that the plant can generate on. For example, `terrafirmacraftplus:Dirt` or `ore:stoneBricks`. For plants added by this mod, this parameter will control what blocks the plant can be placed on. For epiphytic plants you may use tree names to add tree trunks to this list such as `oak`. the keyword `alltrees` adds all possible tree trunks.
- **maxAltitude**
  - The maximum y level that the plant can generate at.
- **maxEVT**
  - The maximum evapotranspiration (EVT) that the plant can generate in.
- **maxRainfall**
  - The maximum rainfall that the plant can generate in.
- **maxTemp**
  - The maximum average temperature that the plant can generate in.
- **metas**
  - The meta values of the block that should be generated using these generation parameters. While generating plants, the meta value of the block placed will be selected randomly from the metas given.
- **minAltitude**
  - The minimum y level that the plant can generate at.
- **minEVT**
  - The minimum evapotranspiration (EVT) that the plant can generate in.
- **minRainfall**
  - The minimum rainfall that the plant can generate in.
-  **minTemp**
  - The minimum average temperature that the plant can generate in.
- **rarity**
  - The rarity of a plant cluster. If `rarity=n` every `n` blocks will have a cluster. For example, if `rarity=256` every chunk will (on average) contain 1 cluster.
- **regions**
  - The regions that the plant can spawn in separated by new lines. valid regions are `Americas, Europe, Africa, Asia`
- **size**
  - The maximum number of plants in a given cluster. The number of plants is determined with a slightly shifted binomial distribution (p=0.5) and so the average number of plants in a cluster is approximately `size/2`. This parameter is ignored for epiphytic plants.
