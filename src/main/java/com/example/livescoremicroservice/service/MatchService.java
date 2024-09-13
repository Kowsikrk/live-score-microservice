package com.example.livescoremicroservice.service;

import com.example.livescoremicroservice.dal.MatchRepository;
import com.example.livescoremicroservice.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match getMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new RuntimeException("Match not found"));
    }

    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    public Match updateMatch(Long id, Match matchDetails) {
        Match match = getMatchById(id);
        match.setTeamA(matchDetails.getTeamA());
        match.setTeamB(matchDetails.getTeamB());
        match.setScoreTeamA(matchDetails.getScoreTeamA());
        match.setScoreTeamB(matchDetails.getScoreTeamB());
        match.setStatus(matchDetails.getStatus());
        return matchRepository.save(match);
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}
