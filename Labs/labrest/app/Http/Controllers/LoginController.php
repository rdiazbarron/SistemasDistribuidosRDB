<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use Tymon\JWTAuth\Facades\JWTAuth;
use Tymon\JWTAuth\Exceptions\JWTException;
use Illuminate\Support\Facades\Auth;


class LoginController extends Controller
{
    // public function login(Request $request)
    // {
    //     $input=$request->all();
    //     $usuario = User::where('email',$input['email'])->first();
    //     if ($usuario == null) {
    //         return response()->json([
    //             'mensaje' => 'email invalido',
    //         ], 400);
    //     }
    //     //verificar usuarioy contraseña
    //     if (Hash::check($request->password, $usuario->password)) {
    //         $key = 'asdasd2##!!dlkajsdleecsd';
    //         $algorithm = 'HS256';
    //         $time = time();
    //         $payload = array(
    //             'iat' => $time, // Tiempo que inició el token
    //             'exp' => $time + 60*60, // Tiempo que expirará el token (+1 hora)
    //             'data' => [ // información del usuario
    //                 'user' => $usuario,
    //             ],
    //         );
    //         $jwt = JWT::encode($payload, $key, $algorithm);
    //         return response()->json([
    //                  'authorization' => [
    //                     'token' => $jwt,
    //                     'type' => 'bearer',
    //                     'expires' => $time ,
    //                 ],
    //         ], 200);
    //     } else {
    //         return response()->json([
    //             'mensaje' => 'Contraseña invalida',
    //         ], 400);
    //     }
    // }
    public function login(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'email' => 'required|email',
            'password' => 'required|string|min:6',
        ]);
    
        if ($validator->fails()) {
            return response()->json($validator->errors(), 422);
        }
    
        $credentials = request(['email', 'password']);
    
        if (!$token = auth('api')->attempt($credentials)) {
            return response()->json(['error' => 'Unauthorized'], 401);
        }
    
        return $this->respondWithToken($token);
    }
    protected function respondWithToken($token)
    {
        
        \Log::info('Token generado:', ['token' => $token]);
        return response()->json([
            'access_token' => $token,
            'token_type' => 'bearer',
            'expires_in' => auth('api')->factory()->getTTL() * 60
        ]);
    }
}