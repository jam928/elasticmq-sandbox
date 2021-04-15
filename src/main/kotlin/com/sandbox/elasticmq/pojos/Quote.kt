package com.sandbox.elasticmq.pojos

import com.fasterxml.jackson.annotation.JsonProperty

class Quote() {
    @JsonProperty("text")
    private val text: String? = null

    @JsonProperty("author")
    private val author: String? = null

    override fun toString(): String {
        return "Quote(text=$text, author=$author)"
    }

}