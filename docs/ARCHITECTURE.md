# RustForge architecture

## Boundary principle
Minecraft and Fabric are Java-first. Java remains responsible for mod loading, lifecycle callbacks, mixins, and interactions with Java mod APIs. Rust is reserved for isolated workloads that profiling shows can benefit from native execution.

## Layers
1. **Minecraft / Fabric (Java):** lifecycle and compatibility.
2. **RustForge Java API:** safe hooks and input validation.
3. **JNI bridge:** narrow, versioned boundary with failure handling.
4. **Rust runtime:** native algorithms and future compute-heavy modules.
5. **Compatibility services (future):** inspect metadata and report dependency/conflict information without silently rewriting other mods.

## Native-work rules
- Profile first; do not move code to Rust based on assumptions.
- Avoid frequent JNI calls in per-frame or per-block hot paths.
- Batch data across JNI where possible.
- Never retain raw Minecraft object pointers in Rust beyond a call.
- Keep native loading optional and Java-only mode safe.
- Validate all data crossing the boundary.

## Mod management direction
A future manager can inspect Fabric metadata, dependencies, entrypoints, and environment constraints. Conflict detection should provide explainable diagnostics. Installing, removing, or changing another mod must always be explicit and reversible.
