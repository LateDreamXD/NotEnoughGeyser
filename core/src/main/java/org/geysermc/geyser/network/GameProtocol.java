/*
 * Copyright (c) 2019-2022 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.geyser.network;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v291.Bedrock_v291;
import org.cloudburstmc.protocol.bedrock.codec.v313.Bedrock_v313;
import org.cloudburstmc.protocol.bedrock.codec.v332.Bedrock_v332;
import org.cloudburstmc.protocol.bedrock.codec.v340.Bedrock_v340;
import org.cloudburstmc.protocol.bedrock.codec.v354.Bedrock_v354;
import org.cloudburstmc.protocol.bedrock.codec.v361.Bedrock_v361;
import org.cloudburstmc.protocol.bedrock.codec.v388.Bedrock_v388;
import org.cloudburstmc.protocol.bedrock.codec.v389.Bedrock_v389;
import org.cloudburstmc.protocol.bedrock.codec.v390.Bedrock_v390;
import org.cloudburstmc.protocol.bedrock.codec.v407.Bedrock_v407;
import org.cloudburstmc.protocol.bedrock.codec.v408.Bedrock_v408;
import org.cloudburstmc.protocol.bedrock.codec.v419.Bedrock_v419;
import org.cloudburstmc.protocol.bedrock.codec.v422.Bedrock_v422;
import org.cloudburstmc.protocol.bedrock.codec.v428.Bedrock_v428;
import org.cloudburstmc.protocol.bedrock.codec.v431.Bedrock_v431;
import org.cloudburstmc.protocol.bedrock.codec.v440.Bedrock_v440;
import org.cloudburstmc.protocol.bedrock.codec.v448.Bedrock_v448;
import org.cloudburstmc.protocol.bedrock.codec.v465.Bedrock_v465;
import org.cloudburstmc.protocol.bedrock.codec.v471.Bedrock_v471;
import org.cloudburstmc.protocol.bedrock.codec.v475.Bedrock_v475;
import org.cloudburstmc.protocol.bedrock.codec.v486.Bedrock_v486;
import org.cloudburstmc.protocol.bedrock.codec.v503.Bedrock_v503;
import org.cloudburstmc.protocol.bedrock.codec.v527.Bedrock_v527;
import org.cloudburstmc.protocol.bedrock.codec.v534.Bedrock_v534;
import org.cloudburstmc.protocol.bedrock.codec.v544.Bedrock_v544;
import org.cloudburstmc.protocol.bedrock.codec.v545.Bedrock_v545;
import org.cloudburstmc.protocol.bedrock.codec.v554.Bedrock_v554;
import org.cloudburstmc.protocol.bedrock.codec.v557.Bedrock_v557;
import org.cloudburstmc.protocol.bedrock.codec.v560.Bedrock_v560;
import org.cloudburstmc.protocol.bedrock.codec.v567.Bedrock_v567;
import org.cloudburstmc.protocol.bedrock.codec.v568.Bedrock_v568;
import org.cloudburstmc.protocol.bedrock.codec.v575.Bedrock_v575;
import org.cloudburstmc.protocol.bedrock.codec.v582.Bedrock_v582;
import org.cloudburstmc.protocol.bedrock.codec.v589.Bedrock_v589;
import org.cloudburstmc.protocol.bedrock.codec.v594.Bedrock_v594;
import org.cloudburstmc.protocol.bedrock.codec.v618.Bedrock_v618;
import org.cloudburstmc.protocol.bedrock.codec.v622.Bedrock_v622;
import org.cloudburstmc.protocol.bedrock.codec.v630.Bedrock_v630;
import org.cloudburstmc.protocol.bedrock.codec.v649.Bedrock_v649;
import org.cloudburstmc.protocol.bedrock.codec.v662.Bedrock_v662;
import org.cloudburstmc.protocol.bedrock.codec.v671.Bedrock_v671;
import org.cloudburstmc.protocol.bedrock.codec.v685.Bedrock_v685;
import org.cloudburstmc.protocol.bedrock.codec.v686.Bedrock_v686;
import org.cloudburstmc.protocol.bedrock.codec.v712.Bedrock_v712;
import org.cloudburstmc.protocol.bedrock.codec.v729.Bedrock_v729;
import org.cloudburstmc.protocol.bedrock.codec.v748.Bedrock_v748;
import org.cloudburstmc.protocol.bedrock.codec.v766.Bedrock_v766;
import org.cloudburstmc.protocol.bedrock.codec.v776.Bedrock_v776;
import org.cloudburstmc.protocol.bedrock.codec.v786.Bedrock_v786;
import org.cloudburstmc.protocol.bedrock.codec.v800.Bedrock_v800;
import org.cloudburstmc.protocol.bedrock.netty.codec.packet.BedrockPacketCodec;
import org.geysermc.geyser.session.GeyserSession;
import org.geysermc.mcprotocollib.protocol.codec.MinecraftCodec;
import org.geysermc.mcprotocollib.protocol.codec.PacketCodec;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Contains information about the supported protocols in Geyser.
 */
public final class GameProtocol {

    /**
     * Default Bedrock codec that should act as a fallback. Should represent the latest available
     * release of the game that Geyser supports.
     */
    public static final BedrockCodec DEFAULT_BEDROCK_CODEC = CodecProcessor.processCodec(Bedrock_v800.CODEC.toBuilder()
        .minecraftVersion("1.21.82")
        .build());

    /**
     * A list of all supported Bedrock versions that can join Geyser
     */
    public static final List<BedrockCodec> SUPPORTED_BEDROCK_CODECS = new ArrayList<>();

    /**
     * Java codec that is supported. We only ever support one version for
     * Java Edition.
     */
    private static final PacketCodec DEFAULT_JAVA_CODEC = MinecraftCodec.CODEC;

    static {
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v291.CODEC.toBuilder()
            .minecraftVersion("1.7.0/1.7.1").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v313.CODEC.toBuilder()
            .minecraftVersion("1.8.0/1.8.1").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v332.CODEC.toBuilder()
            .minecraftVersion("1.9.0").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v340.CODEC.toBuilder()
            .minecraftVersion("1.10.0/1.10.1").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v354.CODEC.toBuilder()
            .minecraftVersion("1.11.0 - 1.11.4").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v361.CODEC.toBuilder()
            .minecraftVersion("1.12.0/1.12.1").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v388.CODEC.toBuilder()
            .minecraftVersion("1.13.0 - 1.13.3").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v389.CODEC.toBuilder()
            .minecraftVersion("1.14.0 - 1.14.41").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v390.CODEC.toBuilder()
            .minecraftVersion("1.14.60").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v407.CODEC.toBuilder()
            .minecraftVersion("1.16.0 - 1.16.10").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v408.CODEC.toBuilder()
            .minecraftVersion("1.16.20 - 1.16.61").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v419.CODEC.toBuilder()
            .minecraftVersion("1.16.100/1.16.101").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v422.CODEC.toBuilder()
            .minecraftVersion("1.16.200/1.16.201").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v428.CODEC.toBuilder()
            .minecraftVersion("1.16.210").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v431.CODEC.toBuilder()
            .minecraftVersion("1.16.220/1.16.221").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v440.CODEC.toBuilder()
            .minecraftVersion("1.17.0 - 1.17.2").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v448.CODEC.toBuilder()
            .minecraftVersion("1.17.10/1.17.11").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v465.CODEC.toBuilder()
            .minecraftVersion("1.17.30 - 1.17.34").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v471.CODEC.toBuilder()
            .minecraftVersion("1.17.40/1.17.41").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v475.CODEC.toBuilder()
            .minecraftVersion("1.18.0 - 1.18.2").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v486.CODEC.toBuilder()
            .minecraftVersion("1.18.10 - 1.18.12").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v503.CODEC.toBuilder()
            .minecraftVersion("1.18.30 - 1.18.33").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v527.CODEC.toBuilder()
            .minecraftVersion("1.19.0 - 1.19.2").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v534.CODEC.toBuilder()
            .minecraftVersion("1.19.10/1.19.11").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v544.CODEC.toBuilder()
            .minecraftVersion("1.19.20").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v545.CODEC.toBuilder()
            .minecraftVersion("1.19.21/1.19.22").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v554.CODEC.toBuilder()
            .minecraftVersion("1.19.30/1.19.31").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v557.CODEC.toBuilder()
            .minecraftVersion("1.19.40/1.19.41").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v560.CODEC.toBuilder()
            .minecraftVersion("1.19.50/1.19.51").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v567.CODEC.toBuilder()
            .minecraftVersion("1.19.60 - 1.19.62").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v568.CODEC.toBuilder()
            .minecraftVersion("1.19.63").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v575.CODEC.toBuilder()
            .minecraftVersion("1.19.70 - 1.19.73").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v582.CODEC.toBuilder()
            .minecraftVersion("1.19.80 - 1.19.83").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v589.CODEC.toBuilder()
            .minecraftVersion("1.20.0/1.20.1").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v594.CODEC.toBuilder()
            .minecraftVersion("1.20.10 - 1.20.15").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v618.CODEC.toBuilder()
            .minecraftVersion("1.20.30 - 1.20.32").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v622.CODEC.toBuilder()
            .minecraftVersion("1.20.40/1.20.41").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v630.CODEC.toBuilder()
            .minecraftVersion("1.20.50/1.20.51").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v649.CODEC.toBuilder()
            .minecraftVersion("1.20.60 - 1.20.62").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v662.CODEC.toBuilder()
            .minecraftVersion("1.20.70 - 1.20.73").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v671.CODEC.toBuilder()
            .minecraftVersion("1.20.80/1.20.81").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v685.CODEC.toBuilder()
            .minecraftVersion("1.21.0/1.21.1").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v686.CODEC.toBuilder()
            .minecraftVersion("1.21.2/1.21.3").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v712.CODEC.toBuilder()
            .minecraftVersion("1.21.20 - 1.21.23").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v729.CODEC.toBuilder()
            .minecraftVersion("1.21.30/1.21.31").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v748.CODEC.toBuilder()
            .minecraftVersion("1.21.40 - 1.21.44").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v766.CODEC.toBuilder()
            .minecraftVersion("1.21.50/1.21.51").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v776.CODEC.toBuilder()
            .minecraftVersion("1.21.60 - 1.21.62").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v786.CODEC.toBuilder()
            .minecraftVersion("1.21.70 - 1.21.73").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v766.CODEC.toBuilder()
            .minecraftVersion("1.21.50/1.21.51").build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v776.CODEC.toBuilder()
            .minecraftVersion("1.21.60 - 1.21.62")
            .build()));
        SUPPORTED_BEDROCK_CODECS.add(CodecProcessor.processCodec(Bedrock_v786.CODEC.toBuilder()
            .minecraftVersion("1.21.70 - 1.21.73")
            .build()));
        SUPPORTED_BEDROCK_CODECS.add(DEFAULT_BEDROCK_CODEC.toBuilder()
            .minecraftVersion("1.21.80 - 1.21.82").build());
    }

    /**
     * Gets the {@link BedrockPacketCodec} of the given protocol version.
     * @param protocolVersion The protocol version to attempt to find
     * @return The packet codec, or null if the client's protocol is unsupported
     */
    public static @Nullable BedrockCodec getBedrockCodec(int protocolVersion) {
        for (BedrockCodec packetCodec : SUPPORTED_BEDROCK_CODECS) {
            if (packetCodec.getProtocolVersion() == protocolVersion) {
                return packetCodec;
            }
        }
        return null;
    }

    /* Bedrock convenience methods to gatekeep features and easily remove the check on version removal */

    public static boolean isPreCreativeInventoryRewrite(int protocolVersion) {
        return protocolVersion < 776;
    }

    public static boolean is1_21_70orHigher(GeyserSession session) {
        return session.protocolVersion() >= Bedrock_v786.CODEC.getProtocolVersion();
    }

    public static boolean isTheOneVersionWithBrokenForms(GeyserSession session) {
        return session.protocolVersion() == Bedrock_v786.CODEC.getProtocolVersion();
    }

    public static boolean is1_21_80orHigher(GeyserSession session) {
        return session.protocolVersion() >= Bedrock_v800.CODEC.getProtocolVersion();
    }

    /**
     * Gets the {@link PacketCodec} for Minecraft: Java Edition.
     *
     * @return the packet codec for Minecraft: Java Edition
     */
    public static PacketCodec getJavaCodec() {
        return DEFAULT_JAVA_CODEC;
    }

    /**
     * Gets the supported Minecraft: Java Edition version names.
     *
     * @return the supported Minecraft: Java Edition version names
     */
    public static List<String> getJavaVersions() {
        return List.of(DEFAULT_JAVA_CODEC.getMinecraftVersion());
    }

    /**
     * Gets the supported Minecraft: Java Edition protocol version.
     *
     * @return the supported Minecraft: Java Edition protocol version
     */
    public static int getJavaProtocolVersion() {
        return DEFAULT_JAVA_CODEC.getProtocolVersion();
    }

    /**
     * Gets the supported Minecraft: Java Edition version.
     *
     * @return the supported Minecraft: Java Edition version
     */
    public static String getJavaMinecraftVersion() {
        return DEFAULT_JAVA_CODEC.getMinecraftVersion();
    }

    /**
     * @return a string showing all supported Bedrock versions for this Geyser instance
     */
    public static String getAllSupportedBedrockVersions() {
        StringJoiner joiner = new StringJoiner(", ");
        for (BedrockCodec packetCodec : SUPPORTED_BEDROCK_CODECS) {
            joiner.add(packetCodec.getMinecraftVersion());
        }

        return joiner.toString();
    }

    /**
     * @return a string showing all supported Java versions for this Geyser instance
     */
    public static String getAllSupportedJavaVersions() {
        StringJoiner joiner = new StringJoiner(", ");
        for (String version : getJavaVersions()) {
            joiner.add(version);
        }

        return joiner.toString();
    }

    private GameProtocol() {
    }
}
