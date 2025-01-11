import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.io.IOUtils;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class JsonDiff {

    public enum DeDupStrategy {
        SUM, MAX, MIN
    }

    public static void main(String[] args) throws IOException {
        String test = "v=0\\r\\no=- 4547071945974981795 2 IN IP4 127.0.0.1\\r\\ns=-\\r\\nt=0 0\\r\\na=group:BUNDLE audio\\r\\na=msid-semantic: WMS 35ecc3a7-bf1a-463f-a2e2-f464ebd2d40e\\r\\na=ice-lite\\r\\nm=audio 40013 UDP/TLS/RTP/SAVPF 111 126\\r\\nc=IN IP4 163.70.138.52\\r\\na=rtcp:9 IN IP4 0.0.0.0\\r\\na=candidate:4062257187 1 udp 2113937151 163.70.138.52 40013 typ host generation 0 network-cost 50 ufrag m9PlzIaKPAlU\\r\\na=candidate:3351487160 1 udp 2113939711 2a03:2880:f284:c5:face:b00c:0:6443 40013 typ host generation 0 network-cost 50 ufrag m9PlzIaKPAlU\\r\\na=ice-ufrag:m9PlzIaKPAlU\\r\\na=ice-pwd:9FHgRQ6/Y7QMQt/hFar1sWfYfdDolnsE\\r\\na=fingerprint:sha-256 28:B2:CD:DB:85:3E:4E:20:E8:06:EF:71:E3:21:7A:CC:51:9E:14:8E:65:66:E5:C2:FB:8E:64:4C:68:73:6B:4E\\r\\na=setup:actpass\\r\\na=mid:audio\\r\\na=extmap:1 urn:ietf:params:rtp-hdrext:ssrc-audio-level\\r\\na=extmap:2 http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time\\r\\na=extmap:3 http://www.ietf.org/id/draft-holmer-rmcat-transport-wide-cc-extensions-01\\r\\na=sendrecv\\r\\na=rtcp-mux\\r\\na=rtpmap:111 opus/48000/2\\r\\na=rtcp-fb:111 transport-cc\\r\\na=fmtp:111 minptime=10;useinbandfec=1\\r\\na=rtpmap:126 telephone-event/8000\\r\\na=ssrc:1100176516 cname:p4WEz3MQc3fL86KT\\r\\na=ssrc:1100176516 msid:35ecc3a7-bf1a-463f-a2e2-f464ebd2d40e audio#eU7vBirItgJ\\r\\na=ssrc:1100176516 mslabel:35ecc3a7-bf1a-463f-a2e2-f464ebd2d40e\\r\\na=ssrc:1100176516 label:audio#eU7vBirItgJ\\r\\n";
        String s = test.replaceAll("\\\\r\\\\na=ice-ufrag:[^\\\\r\\\\n]*", "")
                .replaceAll("\\\\r\\\\na=ice-pwd:[^\\\\r\\\\n]*", "")
                .replaceAll("\\\\r\\\\na=ice-lite[^\\\\r\\\\n]*", "");
        System.out.println(test);
        System.out.printf(s);
    }


}
