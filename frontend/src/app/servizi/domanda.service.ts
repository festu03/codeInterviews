import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DomandaService {

  urlDomanda = 'http://localhost:8080/admin/questions'

  constructor(private http: HttpClient) { }

  nuovaDomanda(body: {}) {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(localStorage.getItem('username') + ':' + localStorage.getItem('password'))
    })

    return this.http.post(this.urlDomanda, body, { headers })
  }

  listaDomande(page: number, size: number) {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(localStorage.getItem('username') + ':' + localStorage.getItem('password'))
    })
    return this.http.get(`${this.urlDomanda}?page=${page}&size=${size}`, { headers })
  }
}
