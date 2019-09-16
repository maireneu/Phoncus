import { Component, ViewChild } from '@angular/core';
import { NavController, Slides } from 'ionic-angular';

@Component({
  selector: 'device-custom',
  templateUrl: 'devicecustom.html'
})
export class DeviceCustom {
  @ViewChild(Slides) slides: Slides;

  constructor(public navCtrl: NavController) {
   
  }
  ionViewDidLoad(){
    //this.slides.lockSwipeToNext(true);
  }
    private ispage = [true,false,false,false,false,false];
    private checkarr = [true,false,false,false,false,false]; //slide boolean check;
  slideChanged(){
    //this.slides.lockSwipeToNext(true);
    let currentidx = this.slides.getActiveIndex();
    let max = 0;
    
    for(var i=0;i<this.ispage.length;i++){
      this.ispage[i] = false;
    }
      this.ispage[currentidx] = true;
      
    if(max <= currentidx){
          max = currentidx;
    }
    if(max <= currentidx){
        for(var j=0;j<max+1;j++){
          this.checkarr[j] = true;
        }
    }
  }

  GotoSlide(value){
    let slideidx = value._elementRef.nativeElement.innerText-1;
    if (this.checkarr[slideidx] == false){
      return;
    }
    this.slides.slideTo(slideidx);
  }
  
  GotoNext(){
    //this.slides.lockSwipeToNext(false);
    this.slides.slideNext();
    
  }

  Goback(){
    this.navCtrl.pop();
  }
}
