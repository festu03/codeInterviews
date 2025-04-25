import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';

export const authGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthService)
  const router = inject(Router)

  if (authService.accessoUtente()) {
    return true
  }
  else if (authService.accessoCandidato()) {
    return true
  }
  else {
    router.navigate(['/benvenuto'])
    return false
  }
};

export const authGuardUtente: CanActivateFn = (route, state) => {

  const authService = inject(AuthService)

  if (authService.accessoUtente()) {
    return true
  } else {
    return false
  }
};

export const authGuardCandidato: CanActivateFn = (route, state) => {

  const authService = inject(AuthService)

  if (authService.accessoCandidato()) {
    return true
  } else {
    return false
  }
};