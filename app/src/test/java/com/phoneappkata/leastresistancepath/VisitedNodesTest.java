package com.phoneappkata.leastresistancepath;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class VisitedNodesTest {
    private VisitedNodes visitedNodes = new VisitedNodes();

    private int column = 1;

    private int row = 1;

    private String keyDelimiter = ":";

    @Mock
    private ResistancePath path;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddNewEntry() {
        addEntry();

        assertThat(visitedNodes.get(row, column), is(notNullValue()));
    }

    @Test
    public void shouldReturnValue() {
        addEntry();

        assertThat(visitedNodes.get(row, column), is(path));
    }

    @Test
    public void shouldReturnAlreadyVisitedAsTrueIfEntryExists() {
        addEntry();

        assertThat(visitedNodes.visited(row, column), is(true));
    }

    @Test
    public void shouldReturnAlreadyVisitedAsFalseIEntryDoesNotExists() {
        assertThat(visitedNodes.visited(row, column), is(false));
    }

    private void addEntry() {
        visitedNodes.add(row, column, path);
    }
}