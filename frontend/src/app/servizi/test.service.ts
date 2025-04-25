import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  constructor(private http: HttpClient) { }

  urlInterviews = 'http://localhost:8080/admin/interviews'

  creaTest(body: {}) {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(localStorage.getItem('username') + ':' + localStorage.getItem('password'))
    })
    return this.http.post(this.urlInterviews, body, { headers })
  }

  listaTestCandididato(page: number, size: number) {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(localStorage.getItem('username') + ':' + localStorage.getItem('password'))
    })
    return this.http.get(`${this.urlInterviews}?page=${page}&size=${size}`, { headers })
  }

  listaTestDomanda(interviewId: number) {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(localStorage.getItem('username') + ':' + localStorage.getItem('password'))
    })
    return this.http.get(`${this.urlInterviews}/${interviewId}`, { headers })
  }

  singolaRisposta(interviewId: number, questionId: number) {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(localStorage.getItem('username') + ':' + localStorage.getItem('password'))
    })
    return this.http.get(`${this.urlInterviews}/${interviewId}/questions/${questionId}`, { headers })
  }

}
