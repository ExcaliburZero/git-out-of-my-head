/*
 * Copyright (C) 2016 Christopher Wells <cwellsny@nycap.rr.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dotgitignore.gitoutofmyhead;

/**
 * Objects of the <code>DataAnalyzer</code> class analyze the input given by the
 * MuseServer and send the output as commands to the Controller.
 *
 * @author Christopher Wells <cwellsny@nycap.rr.com>
 */
public class DataAnalyzer {

    final Controller controller;
    private final MuseServer museServer;
    private final int blinkInterval;
    private final int jawClenchInterval;
    int blinkCount;
    int jawClenchCount;

    boolean monitoring;

    /**
     * Initializes a <code>DataAnalyzer</code> with the given buffer size.
     *
     * @param c The Controller of the DataAnalyzer.
     * @param l The double action wait time of the DataAnalyzer.
     * @param p The port of the MuseServer.
     */
    public DataAnalyzer(Controller c, int l, int j, int p) {
        this.controller = c;
        this.blinkInterval = l;
        this.jawClenchInterval = j;
        this.blinkCount = 0;
        this.jawClenchCount = 0;
        this.monitoring = false;
        this.museServer = new MuseServer(this, p);
    }

    public void addBlink() throws InterruptedException {
        System.out.println("Blink: " + blinkCount);
        this.blinkCount++;

        if (!this.monitoring) {
            blinkCount = 1;
            (new BlinkMonitor(this, blinkInterval)).start();
        }
    }
    public void addJawClench() throws InterruptedException {
        System.out.println("Jaw: " + jawClenchCount);
        this.jawClenchCount++;

        if (!this.monitoring) {
            jawClenchCount = 1;
            (new JawClenchMonitor(this, jawClenchInterval)).start();
        }
    }
}
