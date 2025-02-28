import StorageLib.Datastore
import StorageLib.DatastorePref
import StorageLib.RoomKtx
import StorageLib.SecurityPref
import extensions.CORE_EXTENSION
import extensions.implementation

plugins {
    id("commons.android-library")
}

dependencies {
    CORE_EXTENSION
    implementation(SecurityPref)
    implementation(DatastorePref)
    implementation(Datastore)
    implementation(RoomKtx)
}