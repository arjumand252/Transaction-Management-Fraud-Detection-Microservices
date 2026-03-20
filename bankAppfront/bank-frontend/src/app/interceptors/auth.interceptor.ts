import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  if(req.url.includes('/auth/login')) {
    return next(req);
  }
  const token = localStorage.getItem('auth');
  console.log("Interceptor running:", token);

  if (token) {
    const authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });

    return next(authReq);   // ✅ send modified request
  }

  return next(req);
};
