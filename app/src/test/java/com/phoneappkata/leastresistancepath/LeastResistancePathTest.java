package com.phoneappkata.leastresistancepath;

import android.support.annotation.NonNull;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LeastResistancePathTest {

    private LeastResistancePath underTest;

    @Mock
    private ResistancePath path;

    private static String YES = "YES";

    private static String NO = "NO";

    private int resistance = 20;

    private String resistanceString = "20";

    private String resistancePath = "1 2 3";

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldReturnYes() {
        when(path.canFlow()).thenReturn(true);

        assertThat(leastResistancePath().canFlow(), is(YES));
    }

    @NonNull
    private LeastResistancePath leastResistancePath() {
        return new LeastResistancePath(path);
    }

    @Test
    public void shouldReturnNo() {
        when(path.canFlow()).thenReturn(false);

        assertThat(leastResistancePath().canFlow(), is(NO));
    }

    @Test
    public void shouldReturnResistance() {
        when(path.getResistance()).thenReturn(resistance);

        assertThat(leastResistancePath().resistance(), is(resistanceString));
    }

    @Test
    public void shouldReturnPath() {
        when(path.getPath()).thenReturn(newArrayList(1, 2, 3));

        assertThat(leastResistancePath().path(), is(resistancePath));
    }
}
