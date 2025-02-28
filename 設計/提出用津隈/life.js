'use strict'
console.log('lifegame');


//色
class color
{ 
    constructor(R,G,B)
    {
        this.R = R;
        this.G = G;
        this.B = B;
        this.currRed   = Math.floor(Math.random()*this.R);
        this.currGreen = Math.floor(Math.random()*this.G);
        this.currBlue  = Math.floor(Math.random()*this.B);
        
    }

    IniRgb(objOutside,table_rows,LifeField){
        this.AddColor(objOutside);//マスの外側
        this.AddColor(table_rows[Math.floor((Math.random()* LifeField))].cells[Math.floor((Math.random()*LifeField))]);
    }

    AddColor(obj,r,g,b){
        r = this.currRed;
        g = this.currGreen;
        b = this.currBlue;
        obj.style.background= `rgb(${r},${g},${b})`;
    }


}


//ライフの数を管理するクラス
class Countlife
{

    constructor(val)
    {
        
        this.Countlife = function(array,val)
    {
        for(let i =0; i < scale;i++)
        {
            for(let j =0; j < scale;j++)
            {
                array[i][j] =  val[i][j];
            }
        }

        return array;

    }
    }

   
}


//状態遷移クラス
class stateTransition
{}


//座標のクラス
class lifeCoodinate
{
    constructor(x,y)
    {
        this.x = x;
        this.y = y;
    }
 
}






class Main{
    constructor(td)
    {
         this.Table = document.createElement('table');
         this.table = document.querySelector('.table');
         this.Table.classList.add('table');
         let LifeField = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[]];
         this.LifeField = LifeField.length;
         let Interval;
         let Color = new color(255,255,255); //初期化
        let i,j;
        let randomIndex = Math.floor(Math.random()*this.LifeField);
        
        for( i=randomIndex;i < this.LifeField;i++){

            this.tr = document.createElement('tr');
             this.tr.class = 'row'; 
      
             for( j =0 ; j <  this.LifeField;j++)
             {
                 this.td = document.createElement('td');
                 this.td.classList.add(`column`) ;
                 this.table.style= "border:solid yellow 4px;";
                 this.td.style.width=  td;
                 this.td.style.height = td;
                 this.tr.appendChild(this.td);    
             }
 
             this.table.style= "margin: 32px;";
             this.table.appendChild(this.tr);
             this.table.style.overflow = 'hidden';

             
         }  

        
    
//ここで分岐で状態変化させる
         for( i= randomIndex;i < this.LifeField;i++){

            Interval= Math.floor(Math.random()*100);


            Color.AddColor(this.tr,255,255,255);
           
             
             for( j = i+3 ; j >  i; j--)
             {
               
                 this.table.style= "border:solid yellow 4px;";
                 this.td.textContent = LifeField[i][j];
                 
             }     
             setInterval(()=>new color(255,255,255).IniRgb(this.table,this.table.rows,this.LifeField),Interval );//マスの色を・更新
            //  setInterval(,Interval)
            } 


        }
 


}





