<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Expositor extends Model
{
    use HasFactory;
    protected $fillable = ['codigo','titulo','nombre_expositor'];
}
