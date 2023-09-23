package com.mmonge.game.gacha.model.enums;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RareteEnumTest {

    @Test
    public void test_get_ok() {
        assertEquals(RareteEnum.C, RareteEnum.get("C"));
        assertEquals(RareteEnum.S, RareteEnum.get("S"));
        assertEquals(RareteEnum.R, RareteEnum.get("R"));
        assertEquals(RareteEnum.E, RareteEnum.get("E"));
        assertEquals(RareteEnum.L, RareteEnum.get("L"));
        assertEquals(RareteEnum.M, RareteEnum.get("M"));
        assertNull(RareteEnum.get("coucou"));
        assertNull(RareteEnum.get(null));
    }

    @Test
    public void test_get_ko() {
        Logger logger = (Logger) LoggerFactory.getLogger(RareteEnum.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        assertNull(RareteEnum.get("coucou"));
        assertNull(RareteEnum.get(null));

        List<ILoggingEvent> logs = listAppender.list;
        assertSame(2, listAppender.list.size());
    }

    @Test
    public void test_compare() {
        assertSame(-1, RareteEnum.S.compare(RareteEnum.S, RareteEnum.R));
        assertSame(1, RareteEnum.S.compare(RareteEnum.S, RareteEnum.C));
    }
}
