<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use App\Models\User;
use Firebase\JWT\JWT;

class LoginController extends Controller
{
    public function login(Request $request)
    {
        
        $input=$request->all();
        $usuario = User::where('email',$input['email'])->first();
        if ($usuario == null) {
            return response()->json([
                'mensaje' => 'email invalido',
            ], 400);
        }

        if (Hash::check($input['password'], $usuario->password)) {
            $key = 'asdasd2##!!dlkajsdleecsd';// Deberías usar env('JWT_SECRET') para almacenar esta clave pues es sensible
            $algorithm = 'HS256';//hay mas tipos de algoritmos como ser HS384, HS512, RS256, RS384, RS512, ES256, ES384, ES512
            $time = time();
            $payload = array(
                'iat' => $time, // Tiempo que inició el token
                'exp' => $time + 60*60, // Tiempo que expirará el token (+1 hora)
                'data' => [ // información del usuario
                    'user' => $usuario,
                ],
            );

            $jwt = JWT::encode($payload, $key, $algorithm);//Si la contraseña es correcta, se genera un token JWT
            return response()->json([
                'authorization'=> [
                    'token' => $jwt,
                    'type' => 'Bearer',
                    'expires_in' => $time,
                ]
            ], 200);
        } else {
            return response()->json([
                'mensaje' => 'contraseña invalida',
            ], 400);
        }
    }
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        //
    }
}
