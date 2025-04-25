import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _accessoUtente = false
  private _accessoCandidato = false

  constructor() { }

  accessoUtente() {
    const username = localStorage.getItem('username')
    if (username) {
      this._accessoUtente = true
    }
    return this._accessoUtente
  }

  accessoCandidato() {
    const token = localStorage.getItem('token')
    if (token) {
      this._accessoCandidato = true
    }
    return this._accessoCandidato
  }


  disconnessione() {
    if (localStorage.getItem('username') && localStorage.getItem('password')) {
      localStorage.removeItem('username')
      localStorage.removeItem('password')
      this._accessoUtente = false
    } else if (localStorage.getItem('token')) {
      localStorage.removeItem('token')
      localStorage.removeItem('nome candidato')
      this._accessoCandidato = false
    }
  }
}
