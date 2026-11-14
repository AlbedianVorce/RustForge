# RustForge

**A Java–Rust hybrid foundation for Minecraft: Java Edition.**

RustForge is an experimental Fabric mod and native Rust runtime. Its goal is to move suitable, well-isolated workloads into Rust while keeping Minecraft's Java lifecycle and compatibility surface intact.

> **Status:** Early scaffold / proof of concept. Nothing in this repository currently replaces Minecraft's core engine or automatically manages third-party mods.

## Latest release

- **Current version:** `0.1.0`
- **Minecraft:** `1.21.1`
- **Mod loader:** Fabric
- **Java:** 21

Get the installable JAR from [GitHub Releases](https://github.com/AlbedianVorce/RustForge/releases). Download the file named `rustforge-<version>.jar` and place it in your Minecraft instance's `mods` folder. Install Fabric Loader for Minecraft 1.21.1 and Fabric API for the same Minecraft version first.

Do not install the `-sources.jar` file if one is provided; it contains source code, not the playable mod.

> The first release is an experimental Java-side Fabric mod. The Rust native library is not bundled yet; RustForge currently falls back safely when that library is unavailable.

## Design goals

- Keep Fabric/Minecraft integration and mod-facing hooks on the JVM.
- Provide a small, optional JNI boundary to a Rust `cdylib`.
- Make native functionality capability-checked and safe to disable when the library is unavailable.
- Add profiling and benchmarks before moving any workload across the language boundary.
- Build a future mod-inspection and compatibility layer without modifying other mods behind their backs.

## Repository layout

- `src/main/java` — Fabric entrypoint and Java-side native bridge.
- `src/main/resources` — Fabric metadata and mixin configuration.
- `rust/` — Rust native library built as a C-compatible dynamic library.
- `docs/` — architecture notes and development roadmap.

## Requirements

- JDK 21
- Gradle 8.10.2
- Rust stable toolchain (`rustup`, `cargo`) for native development
- Minecraft 1.21.1 and Fabric Loader

## Build from source

Java mod:

```bash
gradle --no-daemon clean build
```

The installable mod JAR is generated in `build/libs/rustforge-<version>.jar`.

Rust library:

```bash
cd rust
cargo build --release
```

The native library must be named and placed where the JVM can find it (for example via `-Djava.library.path=/path/to/native/libs`). The Java mod deliberately remains usable if the native library is absent.

## Versioning and releases

The mod version is defined by `mod_version` in `gradle.properties`. It is injected into Fabric's `fabric.mod.json` and the JAR filename.

To publish a release:
1. Update `mod_version` in `gradle.properties`.
2. Open the repository's **Actions → Build RustForge → Run workflow**.
3. Enter the same version without the `v` prefix (for example, `0.1.0`).
4. After the build succeeds, the workflow creates a GitHub Release with tag `v<version>` and attaches the installable JAR.

Alternatively, pushing a version tag such as `v0.1.0` builds and publishes that tagged version.

## Current scope

The first milestone is a working hybrid skeleton and a verified JNI handshake. Future milestones should introduce measured, opt-in Rust workloads, then mod metadata inspection and compatibility diagnostics. See [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) and [docs/ROADMAP.md](docs/ROADMAP.md).

## License

Apache-2.0. See [LICENSE](LICENSE).
