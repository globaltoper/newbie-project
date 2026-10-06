import { useState, type FormEvent } from 'react'
import { useNavigate } from 'react-router-dom'
import { login } from '../api/memberApi'
import './AuthForm.css'

function LoginPage() {
  const navigate = useNavigate()
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [popup, setPopup] = useState<{ type: 'success' | 'error'; message: string } | null>(null)

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault()
    try {
      await login({ email, password })
      setPopup({ type: 'success', message: '로그인에 성공했습니다.' })
    } catch (err) {
      setPopup({ type: 'error', message: err instanceof Error ? err.message : '로그인에 실패했습니다.' })
    }
  }

  return (
    <div className="auth-page">
      <form className="auth-form" onSubmit={handleSubmit}>
        <h1>로그인</h1>
        <input
          type="email"
          placeholder="이메일"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit" className="primary">
          로그인
        </button>
        <button type="button" className="secondary" onClick={() => navigate('/signup')}>
          회원가입
        </button>
      </form>

      {popup && (
        <div className="popup-overlay" onClick={() => setPopup(null)}>
          <div className="popup" onClick={(e) => e.stopPropagation()}>
            <p>{popup.message}</p>
            <button onClick={() => setPopup(null)}>확인</button>
          </div>
        </div>
      )}
    </div>
  )
}

export default LoginPage
