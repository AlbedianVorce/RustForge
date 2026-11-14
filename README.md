# RustForge

**A Java–Rust hybrid foundation for Minecraft: Java Edition.**

RustForge is an experimental Fabric mod and native Rust runtime. Its goal is to move suitable, well-isolated workloads into Rust while keeping Minecraft's Java lifecycle and compatibility surface intact.

> **Status:** Early scaffold / proof of concept. Nothing in this repository currently replaces Minecraft's core engine or automatically manages third-party mods.

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
- Gradle (the wrapper can be generated with `gradle wrapper`)
- Rust stable toolchain (`rustup`, `cargo`)
- Minecraft 1.21.1 and Fabric Loader

## Build

Java mod:

```bash
gradle build
```

Rust library:

```bash
cd rust
cargo build --release
```

The native library must be named and placed where the JVM can find it (for example via `-Djava.library.path=/path/to/native/libs`). The Java mod deliberately remains usable if the native library is absent.

## Current scope

The first milestone is a working hybrid skeleton and a verified JNI handshake. Future milestones should introduce measured, opt-in Rust workloads, then mod metadata inspection and compatibility diagnostics. See [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) and [docs/ROADMAP.md](docs/ROADMAP.md).

## License

Apache-2.0. See [LICENSE](LICENSE).
