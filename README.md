# RE DimDoors

[![Build](https://github.com/LizzieSpace/RE_DimDoors/actions/workflows/build.yml/badge.svg)](https://github.com/LizzieSpace/RE_DimDoors/actions/workflows/build.yml) [![](https://img.shields.io/badge/powered%20by-Nyx-blue)](https://github.com/mooltiverse/nyx)
## Overview

`re_dimdoors` is a project that encompasses various enhancements and functional expansions over the original DimDoors
mod. This project focuses on improving resource texture management and extending features through custom mixins. In
simple terms: it's just a retexture and mild tampering.


## Features

- **Resource Pack**:
  - Switched the warp texture in favor of end portal texture.
- **Custom Mixin**:
- Added `DetachedRiftBlockEntityMixin` to control decay spread (it's now radial).

## Getting Started

Just pop the newest release on your `mods` folder along with dimdoors and ya should be good!

### Early access previews

check out the newest [Builds](https://github.com/LizzieSpace/RE_DimDoors/actions/workflows/build.yml)!
On the artifacts section, you will find the built jar file you can use.
Just download it, unzip it and test it out!

> [!CAUTION]  
> Previews are under development and possibly unstable...  
> So be a good bean and backup your world lest things get funky!
---

## Contributing

We welcome contributions! Please fork the repository and create a pull request for review.
### Prerequisites

- Java 17

### Building

Clone the repository:

```sh
git clone https://github.com/LizzieSpace/re_dimdoors.git
cd re_dimdoors
```

Build the project:

```sh
chmod +x gradlew
./gradlew build
```

### Running

you may run the project in a test environmet with the gradle task `runClient`

```sh
./gradlew runClient
```

## License

This project is licensed under the GNU GPL v3 License. See the [LICENSE](LICENSE.md) for details.

## Contact

For any queries or support, please contact [Liz C.](mailto:alicecfire@gmail.com).

---
Signed-off-by: Liz C.