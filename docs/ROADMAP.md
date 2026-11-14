# Roadmap

## Phase 0 — foundation
- [x] Java + Rust project layout
- [x] Optional JNI native-library handshake
- [ ] Verify Gradle and Cargo builds in CI
- [ ] Add development run configuration

## Phase 1 — observability
- [ ] Add profiling hooks and baseline benchmarks
- [ ] Record bridge call counts and durations
- [ ] Define a versioned bridge API

## Phase 2 — first native workload
- [ ] Select one isolated workload using profiling evidence
- [ ] Implement Java fallback and Rust implementation
- [ ] Add correctness tests and performance benchmarks

## Phase 3 — mod ecosystem tools
- [ ] Parse Fabric mod metadata and dependency declarations
- [ ] Report missing dependencies and likely conflicts
- [ ] Add explicit, reversible mod-profile management

## Phase 4 — RustForge extension API
- [ ] Design a stable API for optional RustForge modules
- [ ] Document security, lifecycle, and compatibility guarantees
- [ ] Publish example extension projects
