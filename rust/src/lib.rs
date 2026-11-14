use jni::objects::JClass;
use jni::sys::jstring;
use jni::JNIEnv;

const VERSION: &str = concat!("RustForge native ", env!("CARGO_PKG_VERSION"));

/// JNI handshake called by NativeBridge.version().
#[no_mangle]
pub extern "system" fn Java_dev_albedianvorce_rustforge_NativeBridge_nativeVersion(
    mut env: JNIEnv,
    _class: JClass,
) -> jstring {
    env.new_string(VERSION)
        .expect("could not allocate Java version string")
        .into_raw()
}
