import { Component } from '@angular/core';
import { HttpService } from '../http/http';

@Component({
  selector: 'skt-scrap',
  templateUrl: './skt-scrap.component.html',
  providers: [HttpService]
})
export class SktScrapComponent {

  private loading:string="";
  private Prod=[];
  private num:number = 0;

  constructor(public HttpService: HttpService) {

    this.Prod=[
      {PROD_ID : "NA00005292", PROD_GRP_ID: "000000057" ,PROD_GRP_NM: "T 시그니처"},
      {PROD_ID : "NA00005293", PROD_GRP_ID: "000000057" ,PROD_GRP_NM: "T 시그니처"},
      {PROD_ID : "NA00005134", PROD_GRP_ID: "000000042" ,PROD_GRP_NM: "band 데이터"},
      {PROD_ID : "NA00004775", PROD_GRP_ID: "000000042" ,PROD_GRP_NM: "band 데이터"},
      {PROD_ID : "NA00004773", PROD_GRP_ID: "000000042" ,PROD_GRP_NM: "band 데이터"},
      {PROD_ID : "NA00004772", PROD_GRP_ID: "000000042" ,PROD_GRP_NM: "band 데이터"},
      {PROD_ID : "NA00004771", PROD_GRP_ID: "000000042" ,PROD_GRP_NM: "band 데이터"},
      {PROD_ID : "NA00004769", PROD_GRP_ID: "000000042" ,PROD_GRP_NM: "band 데이터"},
      {PROD_ID : "NA00003901", PROD_GRP_ID: "000000035" ,PROD_GRP_NM: "전국민 무한"},
      {PROD_ID : "NA00003900", PROD_GRP_ID: "000000035" ,PROD_GRP_NM: "전국민 무한"},
      {PROD_ID : "NA00004359", PROD_GRP_ID: "000000035" ,PROD_GRP_NM: "전국민 무한"},
      {PROD_ID : "NA00003899", PROD_GRP_ID: "000000035" ,PROD_GRP_NM: "전국민 무한"},
      {PROD_ID : "NA00004084", PROD_GRP_ID: "000000035" ,PROD_GRP_NM: "전국민 무한"},
      {PROD_ID : "NA00003898", PROD_GRP_ID: "000000031" ,PROD_GRP_NM: "T끼리"},
      {PROD_ID : "NA00003897", PROD_GRP_ID: "000000031" ,PROD_GRP_NM: "T끼리"},
      {PROD_ID : "NA00003896", PROD_GRP_ID: "000000031" ,PROD_GRP_NM: "T끼리"},
      {PROD_ID : "NA00003895", PROD_GRP_ID: "000000031" ,PROD_GRP_NM: "T끼리"},
      {PROD_ID : "NA00004827", PROD_GRP_ID: "000000050" ,PROD_GRP_NM: "band 팅"},
      {PROD_ID : "NA00004826", PROD_GRP_ID: "000000050" ,PROD_GRP_NM: "band 팅"},
      {PROD_ID : "NA00004825", PROD_GRP_ID: "000000050" ,PROD_GRP_NM: "band 팅"},
      {PROD_ID : "NA00004294", PROD_GRP_ID: "000000032" ,PROD_GRP_NM: "T끼리 팅"},
      {PROD_ID : "NA00004293", PROD_GRP_ID: "000000032" ,PROD_GRP_NM: "T끼리 팅"},
      {PROD_ID : "NA00004292", PROD_GRP_ID: "000000032" ,PROD_GRP_NM: "T끼리 팅"},
      {PROD_ID : "NA00003407", PROD_GRP_ID: "000000037" ,PROD_GRP_NM: "LTE 팅"},
      {PROD_ID : "NA00003406", PROD_GRP_ID: "000000037" ,PROD_GRP_NM: "LTE 팅"},
      {PROD_ID : "NA00003405", PROD_GRP_ID: "000000037" ,PROD_GRP_NM: "LTE 팅"},
      {PROD_ID : "NA00003408", PROD_GRP_ID: "000000037" ,PROD_GRP_NM: "LTE 팅"},
      {PROD_ID : "NA00004891", PROD_GRP_ID: "000000037" ,PROD_GRP_NM: "LTE 팅"},
      {PROD_ID : "NA00004810", PROD_GRP_ID: "000000051" ,PROD_GRP_NM: "band 어르신"},
      {PROD_ID : "NA00004809", PROD_GRP_ID: "000000051" ,PROD_GRP_NM: "band 어르신"},
      {PROD_ID : "NA00004808", PROD_GRP_ID: "000000051" ,PROD_GRP_NM: "band 어르신"},
      {PROD_ID : "NA00004934", PROD_GRP_ID: "000000051" ,PROD_GRP_NM: "band 어르신"},
      {PROD_ID : "NA00004442", PROD_GRP_ID: "000000033" ,PROD_GRP_NM: "골든에이지"},
      {PROD_ID : "NA00004441", PROD_GRP_ID: "000000033" ,PROD_GRP_NM: "골든에이지"},
      {PROD_ID : "NA00003495", PROD_GRP_ID: "000000033" ,PROD_GRP_NM: "골든에이지"},
      {PROD_ID : "NA00003494", PROD_GRP_ID: "000000033" ,PROD_GRP_NM: "골든에이지"},
      {PROD_ID : "NA00004440", PROD_GRP_ID: "000000038" ,PROD_GRP_NM: "복지"},
      {PROD_ID : "NA00004439", PROD_GRP_ID: "000000038" ,PROD_GRP_NM: "복지"},
      {PROD_ID : "NA00003938", PROD_GRP_ID: "000000038" ,PROD_GRP_NM: "복지"},
      {PROD_ID : "NA00004438", PROD_GRP_ID: "000000038" ,PROD_GRP_NM: "복지"},
      {PROD_ID : "NA00004437", PROD_GRP_ID: "000000038" ,PROD_GRP_NM: "복지"},
      {PROD_ID : "NA00003937", PROD_GRP_ID: "000000038" ,PROD_GRP_NM: "복지"},
      {PROD_ID : "NA00002200", PROD_GRP_ID: "000000039" ,PROD_GRP_NM: "일반형"},
      {PROD_ID : "NA00005017", PROD_GRP_ID: "000000059" ,PROD_GRP_NM: "band YT"},
      {PROD_ID : "NA00005016", PROD_GRP_ID: "000000059" ,PROD_GRP_NM: "band YT"},
      {PROD_ID : "NA00005014", PROD_GRP_ID: "000000059" ,PROD_GRP_NM: "band YT"},
      {PROD_ID : "NA00005013", PROD_GRP_ID: "000000059" ,PROD_GRP_NM: "band YT"}
    ];

  }

  onStart(){
    this.loading = "스크랩을 시작합니다 - 로딩 중";
    this.num=0;
    this.onSubmit();

  }

  onSubmit(){
    if(this.num<this.Prod.length){

      this.HttpService.SktService(this.Prod[this.num]['PROD_ID'],this.Prod[this.num]['PROD_GRP_ID'],this.Prod[this.num]['PROD_GRP_NM'])
      .then(data=>{
        let tmp = {
          "go" : data
        }
        return this.HttpService.SktInput(tmp)
      })
      .then(data=>{
        this.loading = "스크랩을 진행 중 입니다 - " + this.num + "/" + this.Prod.length;
        if(data=="success"){
          this.num++;
          this.onSubmit()
        }else{
          this.loading = "진행 중 에러가 발생했습니다 -" + this.num;
          this.num=0;
        }
      });
    }else{
      this.num=0;
      this.loading = "스크랩이 완료되었습니다";
    }

    // this.HttpService.SktInput(data).then(tmp=>{
    //   console.log("complete");
    // });



  }

}
