#include <jni.h>
#include <cstring>
#include <cstdio>
#include "security.cpp"

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunused-parameter"

// Encode Host
static const char* domainHost = "aHR0cHM6Ly9hcGkuZ2l0aHViLmNvbS8="; // https://api.github.com/

extern "C" jstring
Java_com_dienty_common_remote_di_RemoteModule_decodeDomainHost(JNIEnv *env, jobject thiz, jboolean debug) {
    return env->NewStringUTF(security_decode(domainHost).c_str());
}

#pragma clang diagnostic pop