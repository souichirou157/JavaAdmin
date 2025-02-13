"use strict";
//import {RowCount,rows} from './model'
class ADminPage {
    constructor() {
        this.num = 1;
        this.title = document.getElementById('title');
        this.dashbord = document.getElementById('dashbord');
        this.dashbordTrriger = document.getElementById('dashbordTrriger');
        this.trriger = document.getElementById('Trriger');
        this.DataFieled = document.getElementById('DataFieled');
        this.tableName = document.getElementById('tablename');
        this.select = document.querySelector('.page');
        this.next = document.getElementById('next');
        this.prev = document.getElementById('prev');
        this.count = document.getElementById('count');
        this.flag = 1;
        window.resizeTo(800, 800);
    }
    upDateFlag(flag) {
        this.flag = flag;
    }
    fullScreen() {
        this.title.style.display = "none";
        this.dashbord.style.display = "none";
        this.dashbordTrriger.style.width = "1800px";
        this.DataFieled.style.width = "1800px";
    }
    reversLayer() {
        this.title.style.all = "revert-layer";
        this.dashbord.style.all = "revert-layer";
        this.dashbordTrriger.style.width = "var(--rightRegionWidth)";
        this.DataFieled.style.width = "var(--rightRegionWidth)";
    }
    getFlag() {
        return this.flag;
    }
    viewChange() {
        if (this.getFlag() == 1) {
            this.fullScreen();
            this.upDateFlag(0);
        }
        else if (this.getFlag() == 0) {
            this.reversLayer();
            this.upDateFlag(1);
        }
    }
    addColumn() { return; }
}
const admin = new ADminPage();
