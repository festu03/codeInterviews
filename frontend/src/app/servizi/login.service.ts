import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  constructor(private http: HttpClient) { }

  urlUtente = 'http://localhost:8080/admin/helloworld'
  urlCandidato = 'http://localhost:8080/candidate/user-info'

  loginUtente(username: string, password: string) {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password)
    })

    return this.http.get(this.urlUtente, { headers })
  }

  loginCandidato(token: string){
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + token
    })

    return this.http.get(this.urlCandidato, { headers })
  }
}
