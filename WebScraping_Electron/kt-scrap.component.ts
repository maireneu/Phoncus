import { Component } from '@angular/core';
import { HttpService } from '../http/http';
// import {ChoreService} from '../chore/chore';

interface KtInputInterface {
  prodNm: string,
  prdcCd: string,
  prodType: number,
  makrCd: string,
  sortProd: string,
  pageNo: number,
  searchNm?: string
}

export const KtInput: KtInputInterface = {
  prodNm: "mobile",
  prdcCd: "KJPLTE069",
  prodType: 15,
  makrCd: "",
  sortProd: "HOT",
  pageNo: 0,
  searchNm: ""
}

@Component({
  selector: 'kt-scrap',
  templateUrl: './kt-scrap.component.html',
  providers: [HttpService]
})
export class KtScrapComponent {
  private submitted;
  private KtInput;
  private prdcCditem;
  private pageNoitem;
  private result2;
  private tmp;
  private tmp_result = [];
  private final_result = {};
  private plan_order: number = 0;
  private result3;

  constructor(public HttpService: HttpService) {
    this.submitted = true;
    this.KtInput = KtInput;
    this.prdcCditem = [
      { id: 'KJPLTE089', name: 'LTE 데이터 선택 109' },
      { id: 'KJPLTE069', name: 'LTE 데이터 선택 76.8' },
      { id: 'KJPLTE059', name: 'LTE 데이터 선택 65.8' },
      { id: 'KJPLTE049', name: 'LTE 데이터 선택 54.8' },
      { id: 'KJPLTE044', name: 'LTE 데이터 선택 49.3' },
      { id: 'KJPLTE039', name: 'LTE 데이터 선택 43.8' },
      { id: 'KJPLTE034', name: 'LTE 데이터 선택 38.3' },
      { id: 'KJPLTE029', name: 'LTE 데이터 선택 32.8' },
      { id: 'PYTLTE059', name: 'Y24 65.8' },
      { id: 'PYTLTE049', name: 'Y24 54.8' },
      { id: 'PYTLTE044', name: 'Y24 49.3' },
      { id: 'PYTLTE039', name: 'Y24 43.8' },
      { id: 'PYTLTE034', name: 'Y24 38.3' },
      { id: 'PYTLTE029', name: 'Y24 32.8' },
      { id: 'TYTLTE349', name: 'Y틴 38' },
      { id: 'TYTLTE299', name: 'Y틴 32' },
      { id: 'TYTLTE249', name: 'Y틴 27' },
      { id: 'TYTLTE199', name: 'Y틴 20' },
      { id: 'YJUNRLTE1', name: 'Y주니어 19.8' },
      { id: 'GDN275LTN', name: '순 골든20' },
      { id: 'LTEDEFPLN', name: '순 손말나눔53' },
      { id: 'LTEDEFCHN', name: '순 손말나눔27' },
      { id: 'LTEDEFCAN', name: '순 소리나눔27' },
      { id: 'LTERAVUTE', name: '순 완전무한99' },
      { id: 'LTERAVUTD', name: '순 완전무한77' },
      { id: 'LTERAVUTB', name: '순 완전무한67' },
      { id: 'LTERAVUTC', name: '순 완전무한61' },
      { id: 'LTERAVUTA', name: '순 완전무한51' },
      { id: 'OLEHRALIN', name: '순 모두다올레41' },
      { id: 'OLEHRASLN', name: '순 모두다올레34' },
      { id: 'LTERAVSSN', name: '순 모두다올레28' },
      { id: 'LTERAVUTA', name: '순 광대역 안심무한51' },
      { id: 'CPRTPPLTN', name: '순 제휴51' },
      { id: 'KJPLTE4J1', name: 'LTE 안심 데이터 38.5' },
      { id: 'LTENARLOV', name: 'olleh 나라사랑' },
      { id: 'BIGIGEN45', name: '청소년 지니 안심' },
      { id: 'BIGIGEN25', name: '청소년 지니 베이직' },
      { id: 'BIGIGEN25', name: '순 청소년 안심데이터34' },
      { id: 'BIGI34LTN', name: '순 알27' },
      { id: 'WSTD2', name: '표준' }
    ];
    this.pageNoitem = [
      1, 2, 3, 4, 5
    ];
  }

  onStart(){
    this.result2 = "스크랩을 시작합니다 - 로딩 중"
    this.onSubmit();
  }

  onSubmit() {
    //this.prdcCditem.length
    if (this.plan_order < this.prdcCditem.length) {
      this.KtInput.prdcCd = this.prdcCditem[this.plan_order].id;
      console.log();
      this.KtInput.pageNo = this.KtInput.pageNo + 1;
      this.HttpService.KtService(this.KtInput)
        .then(data => {
          // ///console.log(data);
          this.tmp = data;

          if (this.tmp.LIST_DATA.length > 0) {

            for (let i = 0; i < this.tmp.LIST_DATA.length; i++) {
              let resultObj = {
                "petname": "",
                "factoryprice": "",
                "publicsupportamt": "",
                "storesupportamt": "",
                "realprice": "",
                "disclosureday": ""
              };
              let tmp2 = this.tmp.LIST_DATA[i];
              //console.log(this.tmp.LIST_DATA[i]);
              resultObj.petname = tmp2.prodNm;
              resultObj.factoryprice = tmp2.ofwAmt;
              resultObj.publicsupportamt = tmp2.ktSuprtAmt;
              resultObj.storesupportamt = tmp2.storSuprtAmt;
              resultObj.realprice = tmp2.realAmt;
              resultObj.disclosureday = tmp2.spnsrPunoDate;
              this.tmp_result.push(resultObj);
            }

            this.onSubmit();

          } else {


            let tmp_plan_order = this.plan_order;
            let tmp_tmp_result = this.tmp_result;


            this.final_result[this.prdcCditem[tmp_plan_order].id] = tmp_tmp_result;
            //this.result3 += tmp_plan_order + ' : '+ this.prdcCditem[tmp_plan_order].id+',';
            this.plan_order++;
            this.result2 = "스크랩 진행중입니다 :: " + this.plan_order + "/" + this.prdcCditem.length;
            this.KtInput.pageNo=0;
            this.tmp_result=[];
            this.onSubmit();


          }

          //console.log(this.result);
        });
    }else{
      //this.result2 = JSON.stringify(this.final_result);

      console.log(this.final_result);
      this.HttpService.KtInput(this.final_result).then(data=>{
        if(data["try"]=="success"){
          this.result2 = "완료되었습니다"
        }

      });
    };
  }

}
