/*
 * Copyright (c) 2020 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.matrix.android.api.session.widgets.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import im.vector.matrix.android.api.util.JsonDict

@JsonClass(generateAdapter = true)
data class WidgetContent(
        @Json(name = "creatorUserId") val creatorUserId: String? = null,
        @Json(name = "id") val id: String? = null,
        @Json(name = "type") val type: String? = null,
        @Json(name = "url") val url: String? = null,
        @Json(name = "name") val name: String? = null,
        @Json(name = "data") val data: JsonDict = emptyMap(),
        @Json(name = "waitForIframeLoad") val waitForIframeLoad: Boolean = false
) {

    fun isActive() = type != null && url != null

    /**
     * @return the human name
     */
    fun getHumanName(): String {
        return if (!name.isNullOrBlank()) {
            "$name widget"
        } else if (!type.isNullOrBlank()) {
            when {
                type.contains("widget") -> {
                    type
                }
                id != null              -> {
                    "$type $id"
                }
                else                    -> {
                    "$type widget"
                }
            }
        } else {
            "Widget $id"
        }
    }
}
