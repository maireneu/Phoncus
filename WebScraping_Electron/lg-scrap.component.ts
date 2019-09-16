import { Component } from '@angular/core';
import { HttpService } from '../http/http';

@Component({
  selector: 'lg-scrap',
  templateUrl: './lg-scrap.component.html',
  providers: [HttpService]
})
export class LGScrapComponent {

  private loading: string = "";
  private Prod = [];
  private num: number = 0;

  constructor(public HttpService: HttpService) {

    this.Prod = [
      {priceCodeSelect: "LPZ0000195", text: "데이터 일반 | 월정액 32,890원"},
      {priceCodeSelect: "LPZ0000196", text: "데이터 1.3 | 월정액 39,490원"},
      {priceCodeSelect: "LPZ0000197", text: "데이터 2.3 | 월정액 46,090원"},
      {priceCodeSelect: "LPZ0000198", text: "데이터 3.6 | 월정액 51,590원"},
      {priceCodeSelect: "LPZ0000199", text: "데이터 6.6 | 월정액 55,990원"},
      {priceCodeSelect: "LPZ0000200", text: "데이터 스페셜 A | 월정액 65,890원"},
      {priceCodeSelect: "LPZ0002245", text: "데이터 스페셜 B | 월정액 74,800원"},
      {priceCodeSelect: "LPZ0000300", text: "데이터 스페셜 C | 월정액 88,000원"},
      {priceCodeSelect: "LPZ0000301", text: "데이터 스페셜 D | 월정액 110,000원"},
      {priceCodeSelect: "LPZ0001481", text: "LTE망내34"},
      {priceCodeSelect: "LPZ0001482", text: "LTE망내42"},
      {priceCodeSelect: "LPZ0001483", text: "LTE망내52"},
      {priceCodeSelect: "LPZ0002027", text: "청소년 스페셜 | 월정액 32,890원"},
      {priceCodeSelect: "LPZ0001394", text: "LTE34"},
      {priceCodeSelect: "LPZ0001395", text: "LTE42"},
      {priceCodeSelect: "LPZ0001396", text: "LTE52"},
      {priceCodeSelect: "LPZ0001397", text: "LTE62"}

    ];

  }

  onStart() {
    this.loading = "스크랩을 시작합니다 - 로딩 중";
    this.num = 0;
    this.onSubmit();

  }

  onSubmit() {

    if(this.num<this.Prod.length){
      this.HttpService.LGService(this.Prod[this.num]["priceCodeSelect"])
        .then(data => {
          let tmp = {
            go : data,
            priceCodeSelect : this.Prod[this.num]["priceCodeSelect"]
          }
          console.log(this.Prod[this.num]["priceCodeSelect"]);
          return this.HttpService.LGInput(tmp)
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
        })

      // this.HttpService.SktInput(data).then(tmp=>{
      //   console.log("complete");
      // });

    }else{
      this.num=0;
      this.loading = "스크랩이 완료되었습니다";
    }

  }

}
