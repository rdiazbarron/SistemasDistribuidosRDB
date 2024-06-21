<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Temps extends Model
{
    use HasFactory;
    protected $fillable = ['fecha','temperatura'];
}
