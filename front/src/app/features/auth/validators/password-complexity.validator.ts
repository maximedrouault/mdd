import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function passwordComplexityValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value = control.value;
    if (!value) {
      return null;
    }

    const hasNumber: boolean = /\d/.test(value);
    const hasUpperCase: boolean = /[A-Z]/.test(value);
    const hasLowerCase: boolean = /[a-z]/.test(value);
    const hasSpecialCharacter: boolean = /[!@#$%^&*(),.?":{}|<>]/.test(value);

    const passwordValid: boolean = hasNumber && hasUpperCase && hasLowerCase && hasSpecialCharacter;

    return !passwordValid ? { passwordComplexity: true } : null;
  };
}
