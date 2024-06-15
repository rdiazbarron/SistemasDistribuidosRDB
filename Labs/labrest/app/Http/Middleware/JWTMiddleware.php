<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\Response;
use Firebase\JWT\JWT;


class JWTMiddleware
{
    /**
     * Handle an incoming request.
     *
     * @param  \Closure(\Illuminate\Http\Request): (\Symfony\Component\HttpFoundation\Response)  $next
     */
    // public function handle(Request $request, Closure $next): Response
    // {
    //     try {

    //         $autorizacion = $request->header('Authorization');
    //         $jwt = substr($autorizacion, 7);
    //         $key = 'asdasd2##!!dlkajsdleecsd';

    //         $datos = JWT::decode($jwt,  new Key($key, 'HS256'));

    //         $request->attributes->add(['usuario' => $datos->data]);
    //     } catch (\Exception $e) {

    //         return response()->json(['status' => 'No autorizado' . $e->getMessage()], 401);
    //     }


    //     return $next($request);
    // }
    public function handle(Request $request, Closure $next): Response
    {
        if (!auth()->user() && !$request->bearerToken()) {
            return response()->json(['error' => 'Unauthenticated'], 401);
        }
        return $next($request);
    }
}
