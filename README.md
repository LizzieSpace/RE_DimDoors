# RE DimDoors

[![Build](https://github.com/LizzieSpace/RE_DimDoors/actions/workflows/build.yml/badge.svg)](https://github.com/LizzieSpace/RE_DimDoors/actions/workflows/build.yml)
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

### Prerequisites

- Java 17 or later
- A compatible build tool (e.g., Gradle)

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

- Add the required version of DimDoors to the `./run/mods` folder
- Run the following commands to download and start the project:

```sh
curl -O https://cdn.modrinth.com/data/b8Q0BxnV/versions/OH74ACY1/dimdoors-5.4.1-fabric.jar
mkdir -p run/mods
mv dimdoors-5.4.1-fabric.jar run/mods/
./gradlew runClient
```

## Contributing

We welcome contributions! Please fork the repository and create a pull request for review.

## License

This project is licensed under the GNU GPL v3 License. See the [LICENSE](LICENSE.md) file for details.

## Contact

For any queries or support, please contact [Liz C.](mailto:alicecfire@gmail.com).

---
Signed-off-by: Liz C.