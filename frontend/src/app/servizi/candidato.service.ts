import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CandidatoService {

  constructor(private http: HttpClient) { }

  urListaDomande = 'http://localhost:8080/candidate/interview'

  urlSingolaDomanda = 'http://localhost:8080/candidate/questions/'

  urlSingolaRisposta = 'http://localhost:8080/candidate/questions/'

  domandeCandidato() {
    const headers = new HttpHeaders({
      Authorization: 'Bearer' + localStorage.getItem('token'),
    })
    return this.http.get(this.urListaDomande, { headers })
  }

  singolaDomanda(questionId: number) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer' + localStorage.getItem('token'),
    })
    return this.http.get(`${this.urlSingolaDomanda}${questionId}`, { headers })
  }

  singolaRisposta(questionId: number, answer: {}) {
    const headers = new HttpHeaders({
      Authorization: 'Bearer' + localStorage.getItem('token'),
    })
    return this.http.post(`${this.urlSingolaRisposta}${questionId}/answer`, answer, { headers })
  }
}

