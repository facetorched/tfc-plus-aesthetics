# TFC+ Aesthetics
World generated decoration blocks to TFC+ to make the world prettier

# Instructions for configuration

### biomes
The biomes that the plant can spawn in separated by new lines. valid biomes are `Ocean, Plains, Lake, High Hills, Swamp, River, Hell, Beach, Gravel Beach, High Hills Edge, Rolling Hills, Mountains, Mountains Edge, High Plains, Deep Ocean, Mountain Range, Mountain Range Edge, Foothills, Shore, Salt Swamp, Lakeshore, Riverbank, River Source, Estuary`
### blockName
The name of the block to generate. For example `terrafirmacraftplus:undergrowth`
### dispersion
The rarity of plants within a given generated cluster. If `dispersion=n` every `n` blocks will have a plant. If `dispersion=1`, every block within a cluster will contain a plant.
### forestGen
The weight associated with forest generation. This parameter directly modifies `rarity` in a way that mimics how TFC+ tree generation occurs. The higher this number the more likely this plant will generate alongside trees. If `forestGen=0.0` the plant's rarity will not be affected. If `forestGen=1.0` the plant will generate in similar locations to trees.
### growOnBlocks
The blocks or OreDictionaries that the plant can generate on. For example, `terrafirmacraftplus:Dirt` or `stoneBricks`. For plants added by this mod, this parameter will control what blocks the plant can be placed on.
### maxAltitude
The maximum y level that the plant can generate at.
### maxEVT
The maximum evapotranspiration (EVT) that the plant can generate in.
### maxRainfall
The maximum rainfall that the plant can generate in.
### maxTemp
The maximum average temperature that the plant can generate in.
### metas
The meta values of the block that should be generated using these generation parameters. While generating plants, the meta value of the block placed will be selected randomly from the metas given.
### minAltitude
The minimum y level that the plant can generate at.
### minEVT
The minimum evapotranspiration (EVT) that the plant can generate in.
### minRainfall
The minimum rainfall that the plant can generate in.
### minTemp
The minimum average temperature that the plant can generate in.
### rarity
The rarity of a plant cluster. If `rarity=n` every `n` blocks will have a cluster. For example, if `rarity=256` every chunk will on average contain 1 cluster.
### regions
The regions that the plant can spawn in separated by new lines. valid regions are `Americas, Europe, Africa, Asia`
### size
The maximum number of plants in a given cluster. The number of plants is determined with a slightly shifted binomial distribution (p=0.5) and so the average number of plants in a cluster is approximately `size/2`
