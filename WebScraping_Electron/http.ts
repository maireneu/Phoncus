import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class HttpService {

  private data;

  constructor(public http: Http) {

  }

  KtService(ktinput) {
    let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8' });
    //let headers = new Headers({ 'Content-Type': 'application/json; charset=utf-8' });

    let options = new RequestOptions({ headers: headers });
    let body = new URLSearchParams();
    this.data = null;
    body.set('prodNm', ktinput.prodNm);
    body.set('prdcCd', ktinput.prdcCd);
    body.set('prodType', ktinput.prodType);
    body.set('makrCd', ktinput.makrCd);
    body.set('sortProd', ktinput.sortProd);
    body.set('pageNo', ktinput.pageNo);
    body.set('searchNm', ktinput.searchNm);

    return new Promise(resolve => {
      this.http.post('http://shop.olleh.com/smart/supportAmtList.json', body)
        .map(res => res.json())
        .subscribe(data => {
          this.data = data;
          resolve(this.data);
        });
    });
  }

  KtInput(obj) {
    let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8' });
    //let headers = new Headers({ 'Content-Type': 'application/json; charset=utf-8' });
    let options = new RequestOptions({ headers: headers });
    let body = obj;

    return new Promise(resolve => {
      this.http.post('http://13.124.11.249:52125/ktscrap', body)
        .map(res => res.json())
        .subscribe(data => {
          this.data = data;
          resolve(this.data);
        });
    });
  }

  SktService(PROD_ID,PROD_GRP_ID,PROD_GRP_NM) {
    let headers = new Headers({ 'Content-Type': 'html' });
    //let headers = new Headers({ 'Content-Type': 'application/json; charset=utf-8' });

    let options = new RequestOptions({ headers: headers });
    let body = new URLSearchParams();
    this.data = null;
    body.set('ORDER_FIELD', 'FACTORY_DT');
    body.set('ORDER_TYPE', 'DESC');
    body.set('MODEL_NW_TYPE', 'LTE');
    body.set('CHG_PROD_ID', "LTE");
    body.set('ADD_PROD_ID', "");
    body.set('SALE_MONTH', "24");
    body.set('SALE_AMT_GRP_ID', "");
    body.set('DIY_PROD_TYPE', "");
    body.set('PROD_TYPE', "BASIC");
    body.set('hidProductId', "");
    body.set('hidProductGrpId', "");
    body.set('PROD_MONTH_FEE', "");
    body.set('UPD_POP_YN', "");

    body.set('PROD_ID', PROD_ID);
    body.set('PROD_GRP_ID', PROD_GRP_ID);
    body.set('PROD_GRP_NM', PROD_GRP_NM);
    body.set('PageNo', "");
    body.set("LIST_COUNT", "200");

    return new Promise(resolve => {
      this.http.post('http://shop.tworld.co.kr/handler/Dantong-SKT', body)
        .map(res => res.text())
        .subscribe(data => {
          this.data = data;
          resolve(this.data);
        });
    });
  }

  SktInput(tml) {
    //let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8' });
    //let headers = new Headers({ 'Content-Type': 'application/json; charset=utf-8' });
    //let options = new RequestOptions({ headers: headers });
    let body = tml;

    return new Promise(resolve => {
      this.http.post('http://13.124.11.249:52125/sktscrap', body)
        .map(res => res.json())
        .subscribe(data => {
          resolve(data);
        });
    });
  }

  LGService(priceCodeSelect) {
    let headers = new Headers({ 'Content-Type': 'html' });
    //let headers = new Headers({ 'Content-Type': 'application/json; charset=utf-8' });

    let options = new RequestOptions({ headers: headers });
    let body = new URLSearchParams();
    this.data = null;

    body.set('deviceType', 'lte');
    body.set('formcType', 'PCFOGLTEA');
    body.set('priceCodeSelect', priceCodeSelect);
    body.set('prodType', "lte");


    return new Promise(resolve => {
      this.http.post('http://shop.uplus.co.kr/sqr/prdt/post/RetriveProdDiscountPostList.hpi', body)
        .map(res => res.text())
        .subscribe(data => {
          this.data = data;
          resolve(this.data);
        });
    });
  }

  LGInput(tml) {
    //let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8' });
    //let headers = new Headers({ 'Content-Type': 'application/json; charset=utf-8' });
    //let options = new RequestOptions({ headers: headers });
    let body = tml;

    return new Promise(resolve => {
      this.http.post('http://13.124.11.249:52125/lgscrap', body)
        .map(res => res.json())
        .subscribe(data => {
          resolve(data);
        });
    });
  }


  // test() {
  //   let headers = new Headers({ 'Content-Type': 'html' });
  //   //let headers = new Headers({ 'Content-Type': 'application/json; charset=utf-8' });
  //
  //   let options = new RequestOptions({ headers: headers });
  //   let body = new URLSearchParams();
  //   this.data = null;
  //
  //   return new Promise(resolve => {
  //     this.http.post('http://www.naver.com', body)
  //       .map(res => res.text())
  //       .subscribe(data => {
  //         this.data = data;
  //         resolve(this.data);
  //       });
  //   });
  // }

}
