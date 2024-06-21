<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Bachiller extends Model
{
    use HasFactory;
    protected $fillable = ['ci','nombre','apellido_paterno','apellido_materno'];
}
