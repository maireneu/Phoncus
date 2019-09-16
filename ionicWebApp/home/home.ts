import { Component } from '@angular/core';

import { NavController } from 'ionic-angular';

import { DeviceCustom } from '../devicecustom/devicecustom';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  private page2hidden: boolean = true;
  private page1hidden: boolean = false;
  constructor(public navCtrl: NavController) {
    
  }
  GoToPage2(){
    this.page2hidden = false;
    this.page1hidden = true;
  }
  GoToDeviceCustom(){
    this.navCtrl.push( DeviceCustom );
  }
}
