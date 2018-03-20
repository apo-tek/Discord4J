/*
 * This file is part of Discord4J.
 *
 * Discord4J is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Discord4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Discord4J.  If not, see <http://www.gnu.org/licenses/>.
 */
package discord4j.core.object.spec;

import discord4j.common.json.request.EmbedRequest;
import discord4j.common.json.request.MessageCreateRequest;
import discord4j.rest.util.MultipartRequest;

import javax.annotation.Nullable;
import java.io.InputStream;

public class MessageCreateSpec implements Spec<MultipartRequest> {

    @Nullable
    private String content;
    @Nullable
    private String nonce;
    private boolean tts;
    private EmbedRequest embed;
    private String fileName;
    private InputStream file;

    public MessageCreateSpec setContent(String content) {
        this.content = content;
        return this;
    }

    public MessageCreateSpec setNonce(String nonce) {
        this.nonce = nonce;
        return this;
    }

    public MessageCreateSpec setEmbed(EmbedSpec embed) {
        this.embed = embed.asRequest();
        return this;
    }

    public MessageCreateSpec setFile(String fileName, InputStream file) {
        this.fileName = fileName;
        this.file = file;
        return this;
    }

    @Override
    public MultipartRequest asRequest() {
        MessageCreateRequest json = new MessageCreateRequest(content, nonce, tts, embed);

        return new MultipartRequest(json, file == null ? null : form ->
                form.multipart(true).file("file", fileName, file, "application/octet-stream"));
    }
}