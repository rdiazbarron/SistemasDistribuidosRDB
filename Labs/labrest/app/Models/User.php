<?php

namespace App\Models;

// use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Tymon\JWTAuth\Contracts\JWTSubject;#agregado

class User extends Authenticatable implements JWTSubject
{
    use HasFactory, Notifiable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array<int, string>
     */
    protected $fillable = [
        'name',
        'role',
        'email',
        'password',
    ];

    /**
     * The attributes that should be hidden for serialization.
     *
     * @var array<int, string>
     */
    protected $hidden = [
        'password',
        'remember_token',
    ];

    /**
     * Get the attributes that should be cast.
     *
     * @return array<string, string>
     */
    protected function casts(): array
    {
        return [
            'email_verified_at' => 'datetime',
            'password' => 'hashed',
        ];
    }


    public function getJWTIdentifier()
    {
        return $this->getKey();
    }
    #Este método se utiliza para obtener el identificador único del usuario que será utilizado como el sub (subject) 
    #en el token JWT. El sub es un claim estándar en JWT que identifica al principal del token, 
    #que en el caso de una aplicación típica es el usuario.
    # Este identificador suele ser el ID del usuario en la base de datos.


    public function getJWTCustomClaims()
    {
        return [];
    }
    #Este método se utiliza para añadir claims adicionales al payload del JWT. Los claims son 
    #declaraciones sobre una entidad (generalmente el usuario) 
    #y sobre datos adicionales del contexto de la autenticación.

}
