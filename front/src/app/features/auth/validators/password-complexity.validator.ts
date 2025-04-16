import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

/**
 * Creates a ValidatorFn that checks if the password meets complexity requirements.
 * The password must include at least one number, one uppercase letter,
 * one lowercase letter, and one special character from [!@#$%^&*(),.?":{}|<>].
 *
 * @returns {ValidatorFn} A validation function that returns an error object if the password
 * does not meet the complexity requirements, otherwise null.
 */
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

