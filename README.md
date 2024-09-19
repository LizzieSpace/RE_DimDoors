# re_dimdoors

[![Build](https://github.com/LizzieSpace/RE_DimDoors/actions/workflows/build_action.yml/badge.svg)](https://github.com/LizzieSpace/RE_DimDoors/actions/workflows/build_action.yml)

## Overview

`re_dimdoors` is a project that encompasses various enhancements and functional expansions over the original DimDoors
mod. This project focuses on improving the resource texture management and extending features through custom mixins.

in simple terms: it's just a retexture and mild tampering

## Features

- **Resource Pack**:
    - switched the warp texture in favor of end portal texture.
- **Custom Mixin**:
    - Added `DetachedRiftBlockEntityMixin` to control decay spread. (it's now radial)

## Getting Started

### Prerequisites

- Java 17 or later.
- A compatible build tool (e.g., Gradle).

### Building

Clone the repository:

```sh
git clone https://github.com/yourusername/re_dimdoors.git
cd re_dimdoors
```

Build the project:

```sh
chmod -x gradlew
./gradlew build
```

### Running

- Add the required version of DimDoors to the ./run/mods folder
- Run the following command to start the project:

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