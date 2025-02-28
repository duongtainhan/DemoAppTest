package com.dienty.core.network.logging

/**
 * HeaderModifier allow for changing header name/value before creating curl log
 */
interface HeaderModifier {
    /**
     * @param header the header to check
     * @return true if header should be modified and false otherwise.
     */
    fun matches(header: CurlHeader): Boolean

    /**
     * @param header the header to modify
     * @return modified header or null to omit header in curl log
     */
    fun modify(header: CurlHeader): CurlHeader?
}